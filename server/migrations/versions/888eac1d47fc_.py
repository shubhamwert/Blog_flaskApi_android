"""empty message

Revision ID: 888eac1d47fc
Revises: 8ed602fcb88d
Create Date: 2020-01-25 10:22:19.666020

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = '888eac1d47fc'
down_revision = '8ed602fcb88d'
branch_labels = None
depends_on = None


def upgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.add_column('post', sa.Column('timestamp', sa.DateTime(), nullable=True))
    op.create_index(op.f('ix_post_timestamp'), 'post', ['timestamp'], unique=False)
    op.drop_index('ix_post_writer_name', table_name='post')
    op.create_foreign_key(None, 'post', 'user', ['writer_name'], ['id'])
    # ### end Alembic commands ###


def downgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.drop_constraint(None, 'post', type_='foreignkey')
    op.create_index('ix_post_writer_name', 'post', ['writer_name'], unique=False)
    op.drop_index(op.f('ix_post_timestamp'), table_name='post')
    op.drop_column('post', 'timestamp')
    # ### end Alembic commands ###